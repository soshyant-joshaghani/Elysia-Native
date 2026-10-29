package elysianative.modules.global

data class NavItem(val href: String, val label: String)

object ShellNav {
    val primary = listOf(
        NavItem("/", "Home"),
        NavItem("/sample/notes", "Notes"),
        NavItem("/admin", "Admin")
    )

    fun title(route: String): String = when (route) {
        "/" -> "Home"
        "/login" -> "Sign in"
        "/sample/notes" -> "Notes"
        "/admin" -> "Admin"
        else -> route
    }

    fun note(route: String): String = when (route) {
        "/" -> "Dashboard home. The web kit renders the same path after you run web use."
        "/login" -> "Sign in against the contract login route."
        "/sample/notes" -> "Canonical sample notes module."
        "/admin" -> "Admin area (superuser only)."
        else -> "This path is not in client-routes.json."
    }

    fun isActive(route: String, item: NavItem): Boolean =
        if (item.href == "/") route == "/" else route == item.href || route.startsWith(item.href + "/")
}
