<#import "base.ftlh" as html>
<#import "/spring.ftl" as sp>

<#assign
page={"pageId": "homepage",
"cssFiles": [],
"javascriptFiles": []}
>
<@html.body page=page>
    <form>
        <div class="form-input-group">
            <label for="username"><@sp.message "login.form.label.username" /></label>
            <input id="username" type="text" name="username" placeholder='<@sp.message "login.form.input.placeholder.username" />'>
        </div>
        <div class="form-input-group">
            <label for="password"><@sp.message "login.form.label.password" /></label>
            <input id="password" type="text" placeholder='<@sp.message "login.form.input.placeholder.password" />'>
        </div>
    </form>
</@html.body>
