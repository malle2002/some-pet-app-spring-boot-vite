import {
    NavigationMenu,
    NavigationMenuItem, NavigationMenuLink,
    NavigationMenuList,
} from "@/components/ui/navigation-menu.tsx";
import {Link} from "react-router-dom";

export default function Navbar() {
    return (
        <nav
            className="fixed top-5 left-1/2 transform -translate-x-1/2
               flex flex-row items-center justify-between gap-x-24
               px-6 py-3 w-[90%] max-w-5xl rounded-full
               shadow-xl backdrop-blur-md bg-gray-300/10"
        >
            <div className={"ml-5 font-urbanist font-bold"}>
                logo
            </div>

            <NavigationMenu>
                <NavigationMenuList className={"gap-x-3"}>
                    <NavigationMenuItem>
                        <Link to={"/"}>
                            <NavigationMenuLink className={"font-semibold"}>Home</NavigationMenuLink>
                        </Link>
                    </NavigationMenuItem>
                    <NavigationMenuItem>
                        <Link to={"/pets"}>
                            <NavigationMenuLink className={"font-semibold"}>Pets</NavigationMenuLink>
                        </Link>
                    </NavigationMenuItem>
                    <NavigationMenuItem>
                        <Link to={"/petsWithoutOwner"}>
                            <NavigationMenuLink className={"font-semibold"}>Pets No Owner</NavigationMenuLink>
                        </Link>
                    </NavigationMenuItem>
                    <NavigationMenuItem>
                        <Link to={"/buy"}>
                            <NavigationMenuLink className={"font-semibold"}>Buy</NavigationMenuLink>
                        </Link>
                    </NavigationMenuItem>
                    <NavigationMenuItem>
                        <Link to={"/user-seed"}>
                            <NavigationMenuLink className={"font-semibold"}>Seed Users</NavigationMenuLink>
                        </Link>
                    </NavigationMenuItem>
                    <NavigationMenuItem>
                        <Link to={"/pet-seed"}>
                            <NavigationMenuLink className={"font-semibold"}>Seed Pets</NavigationMenuLink>
                        </Link>
                    </NavigationMenuItem>
                </NavigationMenuList>
            </NavigationMenu>

            <div className={"mr-5"}>

            </div>
        </nav>
    )
}