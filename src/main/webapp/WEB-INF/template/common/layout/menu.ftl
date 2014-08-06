<!-- Top -->
<div id="top-menu">
	<div id="account">
		<ul>
			<li><a href="/login" class="login">Login</a></li>
		</ul>
	</div>
</div>

<!-- Header -->
<div id="header">
	<h1>${systemName}</h1>
	<div id="main-menu">
		<ul>
			<li><a href="${base}/entry.jhtml" <#if module="entry">class="entries selected"<#else>class="entries"</#if>>Entries</a></li>
			<li><a href="${base}/repository.jhtml" <#if module="repo">class="entries selected"<#else>class="entries"</#if>>My History</a></li>
		</ul>
	</div>
</div>