<div class="navbar-default sidebar" role="navigation">
    <div class="sidebar-nav navbar-collapse">
        <ul class="nav" id="side-menu">

 
        <#list principal.userMenus as menu>
            <li>
                <a href="#"><i class="fa fa-user fa-fw"></i> ${menu.name}<span class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <#assign persissions = menu.permissions>
                    <#list persissions as permission>
                        <li>

     <a href="${permission.url}">${permission.name}</a>                       

                        </li>
                    </#list>
                </ul>
            </li>
        </#list>


        </ul>
    </div>
</div>
