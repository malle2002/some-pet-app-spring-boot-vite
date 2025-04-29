import {useEffect, useState} from "react";
import axios from "@/lib/axios.ts";

export default function LogsContainer() {
    const [logs, setLogs] = useState<string>("");
    useEffect( () => {
        axios.get("api/logs").then((log: any) => setLogs(log.data));
    }, []);
    return (
        <div className="inline-block self-end size-86 mr-24 mb-24">
            <p>Logs</p>
            <textarea value={logs} readOnly={true} className="border h-full w-full"/>
        </div>
    )
}