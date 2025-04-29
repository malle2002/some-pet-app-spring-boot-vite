import {SiteFooter} from "@/components/site-footer.tsx";
import Navbar from "@/components/Navbar.tsx";
import LogsContainer from "@/components/LogsContainer.tsx";

export default function DefaultLayout({ children } : { children: React.ReactNode }) {
    return (
        <main className={"flex flex-col min-h-screen bg-gradient-to-t"}>
            <Navbar/>

            <div className={"flex-grow bg-white"}>
                {children}
            </div>

            <LogsContainer/>
            <SiteFooter/>
        </main>
    )
}