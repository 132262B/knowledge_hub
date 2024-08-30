import "./App.css";
import Viewer from "./components/Viewer";
import Controller from "./components/Controller";
import {useEffect, useRef, useState} from "react";
import {Even} from "./components/Even.jsx";


function App() {
    const [count, setCount] = useState(0);
    const isMount = useRef(false);

    useEffect(() => {
        console.log(count)
    }, [count]);

    useEffect(() => {
        console.log('mount')
    }, [])

    useEffect(() => {
        if(!isMount.current) {
            isMount.current = true
            return;
        }
        console.log('update')
    })



    const onClickButton = (value) => {
        setCount(count + value);
    };

    return (
        <div className="App">
            <h1>Simple Counter</h1>
            <section>
                <Viewer count={count}/>
                {
                    count % 2 === 0 ? <Even /> : null
                }
            </section>
            <section>
                <Controller onClickButton={onClickButton}/>
            </section>
        </div>
    );
}

export default App;
