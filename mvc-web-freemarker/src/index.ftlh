<#import "src/empty.ftlh" as html>

<#assign
page={"title": "home",
"pageId": "homepage",
"cssFiles": [],
"javascriptFiles": []}
>
<@html.body page=page>
    <h1>Hello, World!</h1>
</@html.body>
