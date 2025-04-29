import axios from "@/lib/axios.ts";
import {useEffect, useState} from "react";
import {CardWithForm} from "@/components/Card.tsx";

interface PetDataType {
    id: string,
    name: string,
    dateOfBirth: string,
    price: {
        amount: number,
        currency: string
    },
    description: string
}

export default function PetsPage(withoutOwner: { withoutOwner: boolean }) {
    const [pets, setPets] = useState([]);
    useEffect( () => {
        axios.get("/api/pets" + (withoutOwner ? "?owner=false" : "")).then( (response) =>
            setPets(response.data)
        )
    }, []);
    return (
        <div className="mt-24 ml-24 w-[60%]">
            <h3 className="text-xl">Pets:</h3>
            <div className="grid grid-cols-3 place-items-center space-y-5">
                { pets && (
                    pets.map((pet:PetDataType) =>
                        <CardWithForm petData={pet} key={pet.id}/>
                    )
                )}
            </div>
        </div>
    )
}