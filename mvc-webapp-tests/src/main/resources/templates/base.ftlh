<#macro body page>
    <#import "/spring.ftl" as sp>
    <html lang="en">
    <head>
        <title>${page.title}</title>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="version" content="${serverVersion}">

        <link rel="stylesheet" href="/assets/vendors/bootstrap/css/bootstrap.css">
        <link rel="stylesheet" href="/assets/app/css/main.css">
        <#list page.cssFiles as css>
            <link rel="stylesheet" href="${css}">
        </#list>
    </head>
    <body data-test-page-id="${page.pageId}">
    <header class="container">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="#">Navbar</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Link</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Dropdown
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="#">Action</a>
                            <a class="dropdown-item" href="#">Another action</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">Something else here</a>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link disabled" href="#">Disabled</a>
                    </li>
                </ul>
                <form class="form-inline my-2 my-lg-0">
                    <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                </form>
            </div>
        </nav>
    </header>
    <div class="container">
        <#nested />
    </div>
    <footer>

    </footer>
    <script type="javascript" src="/assets/vendors/jquery-3.3.1.js"></script>
    <script type="javascript" src="/assets/vendors/popper.js"></script>
    <script type="javascript" src="/assets/vendors/bootstrap/js/bootstrap.js"></script>
    <script type="javascript" src="/assets/app/js/main.js"></script>
    <#list page.javascriptFiles as script>
        <script type="javascript" src="${script}"/>
    </#list>
    </body>
    </html>
</#macro>