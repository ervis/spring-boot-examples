<#import "base.ftlh" as html>
<#import "security.ftlh" as sec>
<#import "/spring.ftl" as sp>

<#assign
page={"pageId": "homepage",
"cssFiles": [],
"javascriptFiles": []}
>
<@html.body page=page>

    <div class="row">
        <div class="col-sm">
            <form>
                <div class="form-group">
                    <label for="exampleInputEmail1">Email address</label>
                    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"
                           placeholder="Enter email">
                    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone
                        else.</small>
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">Password</label>
                    <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
                <@sec.csrf />
            </form>
        </div>
    </div>
</@html.body>
