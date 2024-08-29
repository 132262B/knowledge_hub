import './App.css';
import Header from './components/Header';
import Footer from "./components/Footer";
import Main from "./components/Main";
import Button from "./components/Button"

function App() {

    const buttonProps = {
        text: "메일",
        color: "red"
    }

    return (
        <>
            <Button {...buttonProps} />
            <Button text={"카페"} />
            <Button
                on
                text={"블로그"} >
                <div>자식요소</div>
            </Button>
        </>
    )
}

export default App
