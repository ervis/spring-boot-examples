# Handling long running requests

Imagine that you work for an e-shop company, and you need to send
daily orders to a third party system so that they can process them 
and send discounts to specific users.

### Use case
- At the end of the day, the system generates reports as CSV files
- those CSV files are being automatically uploaded to the system
- each line in the CSV file contains the product title,
product category, email of the user who bought it, and the cost
- each file has a unique id that we can use to retrieve 
all the items that were created in that single file upload
- a typical CSV file can contain ~ 2000-3000 items
- in a normal day 100-200 files are being uploaded
- on weekends ~ 400-500
- we can only query the orders by file upload id
```
GET /fist-party/api/orders?file-id=1

{
	"items": [{
			"id": 24,
			"title": "Effective Java",
			"category": "books",
			"user": "john.doe@example.com",
			"price": 54.00
		}, {
			"id": 34,
			"title": "Working with legacy code",
			"category": "books",
			"user": "jane.doe@example.com",
			"price": 55.00
		},
		{
			"id": 42,
			"title": "Clean code",
			"category": "books",
			"user": "jane.doe@example.com",
			"price": 56.00
		}
	]
}
```

The marketing department decided in order to further boost 
the sales to partner with the DiscountInc company that can 
analyze the orders  and suggest discounts for users.  
To do so at the end of the day we send the total orders 
of the day to their system.

- The DiscountInc provides an API that we can use to create orders
- unfortunately we've discovered that creating an order is a two step process.
- first we can create the orders in a single call

```
POST /third-party/api/orders
Input:
{
	"orders": [{
			"clientOrderId": 24,
			"title": "Effective Java",
			"category": "books",
			"price": 54.00
		}, {
			"clientOrderId": 34,
			"title": "Working with legacy code",
			"category": "books",
			"price": 55.00
		},
		{
			"clientOrderId": 42,
			"title": "Clean code",
			"category": "books",
			"price": 56.00
		}
	]
}

output:
{
	"orders": [{
			"orderId": "911f0dde-8179-47b9-a750-0e731c53a351",
			"clientOrderId": 24,
			"title": "Effective Java",
			"category": "books",
			"price": 54.00,
			"userId": null
		}, {
			"orderId": "a8653ad2-9847-42e1-8027-a389ba172a8e",
			"clientOrderId": 34,
			"title": "Working with legacy code",
			"category": "books",
			"price": 55.00,
			"userId": null
		},
		{
			"orderId": "a80e9383-68f5-4353-9038-3e850e22aa39",
			"clientOrderId": 42,
			"title": "Clean code",
			"category": "books",
			"price": 56.00,
			"userId": null
		}
	]
}
```
- and then for each order we use the __orderId__ to associate an oder with a user
```
-------------------------------------------
POST /third-party/api/orders/{order-id}/user
{
    "userId": "john.doe@example.com"
}
```
- we can retrieve a order by ID
```
-------------------------------------------
GET /third-party/api/orders/{order-id}
{
    "orderId": "911f0dde-8179-47b9-a750-0e731c53a351",
    "clientOrderId": 24,
    "title": "Effective Java",
    "category": "books",
    "userId": "john.doe@example.com",
    "price": 54.00
}
```

- To make things worse we've noticed if we try to create 1000
orders in a single requests,
it takes 6 minutes to get a response from the server.
The discInc informed us that they can't improve the response time.
- Assume the response time is linear when creating orders 
(1000 orders in 360 sec, that means on average 360ms/order) 
- The API call that associates a user with an order takes 180ms

### Goals
- Create daily orders using the third-party API
- this daily operation must run from 12:00AM to 07:59:59AM (8* hour window)
- suggest alternative design that optimize the throughput

More:
- read Molina's [original paper](https://www.cs.cornell.edu/andru/cs711/2002fa/reading/sagas.pdf)
on Saga pattern