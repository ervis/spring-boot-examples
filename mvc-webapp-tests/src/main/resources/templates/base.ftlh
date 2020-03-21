<#macro body page>
    <#import "/spring.ftl" as sp>
    <html>
    <head>
        <title><@sp.message "page.homepage.title" /></title>

        <link rel="stylesheet" href="/assets/vendors/bootstrap/css/bootstrap-grid.css">
        <link rel="stylesheet" href="/assets/vendors/bootstrap/css/bootstrap.css">
        <#list page.cssFiles as css>
            <link rel="stylesheet" href="${css}">
        </#list>
    </head>
    <body>
    <header>
    </header>
    <div class="container" data-test-page-id="${page.pageId}">
        <#nested />
    </div>
    <footer>

    </footer>
    <script type="javascript" src="/assets/vendors/bootstrap/js/bootstrap.js"/>
    <#list page.javascriptFiles as script>
        <script type="javascript" src="${script}"/>
    </#list>
    </body>
    </html>
</#macro>