import { Button } from "@/components/ui/button"
import {
    Card,
    CardContent,
    CardDescription,
    CardFooter,
    CardHeader,
    CardTitle,
} from "@/components/ui/card"
import { Label } from "@/components/ui/label"

interface PetDataType {
    name: string,
    dateOfBirth: string,
    price: {
        amount: number,
        currency: string
    },
    description: string
}

export function CardWithForm({ petData } : { petData: PetDataType }) {
    return (
        <Card className="w-[350px]">
            <CardHeader>
                <CardTitle>{ petData.name }</CardTitle>
                <CardDescription>{ petData.dateOfBirth }</CardDescription>
            </CardHeader>
            <CardContent>
                <form>
                    <div className="grid w-full items-center gap-4">
                        <div className="flex flex-col space-y-1.5">
                            <Label htmlFor="name">Description</Label>
                            <textarea className={"border rounded-md"} value={petData.description} readOnly={true}/>
                        </div>
                        <div className="flex flex-col space-y-1.5">
                            <Label htmlFor="framework">Price</Label>
                            <h1>{ petData.price.amount } { petData.price.currency }</h1>
                        </div>
                    </div>
                </form>
            </CardContent>
            <CardFooter className="flex justify-between">
                <Button>Buy</Button>
            </CardFooter>
        </Card>
    )
}