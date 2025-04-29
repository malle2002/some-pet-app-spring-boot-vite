import {useEffect, useState} from "react";
import axios from "@/lib/axios.ts";

interface BuyType {
    executedAt: string;
    successCount: number;
    failureCount: number;
}

export default function Buy() {
    const [log, setLog] = useState<BuyType | null>(null);
    useEffect( () => {
        const timeoutId = setTimeout(() => {
            axios.post("/api/seed/pet/buy").then((response) => {
                console.log(response);
                setLog(response.data);
            });
        }, 2000); // 2000ms = 2 seconds

        return () => clearTimeout(timeoutId);
    }, []);
    if(log == null ) return;
    return (
        <>
            <h1>Executed At: {log.executedAt}</h1>
            <h2>Success Count: {log.successCount}</h2>
            <h2>Failure Count: {log.failureCount}</h2>
        </>
    )
}