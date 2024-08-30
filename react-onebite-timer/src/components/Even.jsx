import {useEffect} from "react";

export const Even = () =>{

    useEffect(() => {

        return () => {
            console.log("unmount")
        }
    }, []);

    return <div>짝수입니다.</div>
}