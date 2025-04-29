import {useEffect, useState} from "react";
import axios from "@/lib/axios.ts";

export default function UserSeed() {
    const [message, setMessage] = useState(null);
    useEffect( () => {
        const timeoutId = setTimeout(() => {
            axios.post("/api/seed/user/create-users").then((response) => {
                setMessage(response.data);
            });
        }, 2000); // 2000ms = 2 seconds

        return () => clearTimeout(timeoutId);
    }, []);
    if(message == null ) return;
    return (
        <>
            <h1>{message}</h1>
        </>
    )
}