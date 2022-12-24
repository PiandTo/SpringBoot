<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div id="navbarNav">
		<ul class="navbar-nav">
			<li class="nav-item active">
                <#if user.role == 'ADMIN'>
					<a class="nav-link" href="/admin">Home</a>
                <#else>
					<a class="nav-link" href="/user">Home</a>
                </#if>
			</li>
			<li class="nav-item active">
				<a class="nav-link" href="/profile">Profile</a>
			</li>
            <#if user.role == 'ADMIN'>
				<li class="nav-item">
					<a class="nav-link" href="/admin/panel/halls">Halls</a>
				</li>
            </#if>
            <#if user.role == 'ADMIN'>
			<li class="nav-item">
				<a class="nav-link" href="/admin/panel/films">Films</a>
			</li>
            <#else>
	            <li class="nav-item">
		            <a class="nav-link" href="/films/">Films</a>
	            </li>
            </#if>
            <#if user.role == 'ADMIN'>
				<li class="nav-item">
					<a class="nav-link" href="/admin/panel/sessions">Sessions</a>
				</li>
            </#if>
			<li>
				<a class="nav-link" href="/sessions/search/">Search</a>
			</li>
			<li class="nav-item nav-link" style="margin-left: 400px;
			">${user.email}</li>
			<li class="nav-item nav-link">
				<form class="btn-logout" name="form1" action="/logout" method="post">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
					<input TYPE="SUBMIT" VALUE="Logout">
				</form>
			</li>
		</ul>
	</div>
</nav>
