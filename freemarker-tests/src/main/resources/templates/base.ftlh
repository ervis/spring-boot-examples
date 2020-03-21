<#macro body page>
    <#import "/spring.ftl" as sp>
    <html>
    <head>
        <title><@sp.message "page.homepage.title" /></title>
        <#list page.cssFiles as css>
            <link rel="stylesheet" href="${css}">
        </#list>
    </head>
    <body>
    <header>
    </header>
    <div class="container" data-test-page-id="${page.pageId}">
    </div>
    <footer>

    </footer>
    <#list page.javascriptFiles as script>
        <script type="javascript" src="${script}"/>
    </#list>
    </body>
    </html>
</#macro>