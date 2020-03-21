<#import "base.ftlh" as html>
<#import "security.ftlh" as sec>
<#import "/spring.ftl" as sp>

<#assign title><@sp.message "login.page.title" /></#assign>
<#assign
page={"title": "${title}",
"pageId": "homepage",
"cssFiles": ["/assets/app/login.css"],
"javascriptFiles": []}
>
<@html.body page=page>
    <div class="row">
        <div class="col-sm">
            <form method="POST"
                  action="/authenticate"
                  class="login-form">
                <div class="form-group">
                    <label for="username"><@sp.message "login.form.label.username" /></label>
                    <input type="email"
                           class="form-control"
                           id="username"
                           name="username"
                           aria-describedby="emailHelp"
                           placeholder="<@sp.message "login.form.input.placeholder.username" />">
                    <small id="emailHelp" class="form-text text-muted"><@sp.message "login.form.email-help" /></small>
                </div>
                <div class="form-group">
                    <label for="password"><@sp.message "login.form.label.password" /></label>
                    <input type="password"
                           name="password"
                           class="form-control"
                           id="password"
                           placeholder="<@sp.message "login.form.input.placeholder.password" />">
                </div>
                <div class="form-group forgot-password-wrapper">
                    <small id="forgot-password"
                           class="form-text text-muted forgot-password float-right">
                        <a href="/forgot-password"><@sp.message "login.form.forgot-password" /></a>
                    </small>
                </div>
                <button type="submit" class="btn btn-primary"><@sp.message "form.button.submit" /></button>
                <@sec.csrf />
            </form>
        </div>
    </div>
</@html.body>