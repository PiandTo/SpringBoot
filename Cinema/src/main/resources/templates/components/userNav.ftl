<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div id="navbarNav">
		<ul class="navbar-nav">
			<li class="nav-item active">
				<a class="nav-link" href="/user">Home</a>
			</li>
			<li class="nav-item active">
				<a class="nav-link" href="/profile">Profile</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="/films/">Films</a>
			</li>
			<li>
				<a class="nav-link" href="/sessions/search/">Search</a>
			</li>
			<li class="nav-item nav-link" style="margin-left: 400px;">${user.email}</li>
			<li class="nav-item nav-link">
				<form class="btn-logout" name="form1" action="/logout" method="POST">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
					<input TYPE="SUBMIT" VALUE="Logout">
				</form>
			</li>
		</ul>
	</div>
</nav>
