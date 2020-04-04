<#macro body page>
    <html lang="en">
    <head>
        <title>${page.title}</title>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- maybe have some stylesheets for all freemarker templates? -->
        <#list page.cssFiles as css>
            <link rel="stylesheet" href="${css}">
        </#list>
    </head>
    <body>

    <#nested />

    <#list page.javascriptFiles as script>
        <script type="javascript" src="${script}"/>
    </#list>
    </body>
    </html>
</#macro>