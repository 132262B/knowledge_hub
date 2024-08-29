import {useState, useRef} from "react";

const Register = () => {
    const [input, setInput] = useState({
        name: "",
        birth: "",
    })

    const countRef = useRef(0)
    console.log(countRef)

    const inputRef = useRef(0)

    const onChange = (e) => {
        countRef.current++;
        setInput({
            ...input,
            [e.target.name]: e.target.value
        })
    }

    const submit = () => {
        if(input.name === '') {
            console.log('이름 입력바람!!')
            inputRef.current.focus()
        }
    }

    return (
        <div>
            <h2>register</h2>
            <div>
                <input
                    ref={inputRef}
                    name="name"
                    value={input.name}
                    onChange={onChange}
                    placeholder={"이름"}
                />
                {input.name}
            </div>
            <div>
                <input
                    name="birth"
                    type="date"
                    onChange={onChange}
                /> {input.birth}
            </div>
            <div>
                <button onClick={submit}>
                    제출
                </button>
            </div>
        </div>
    )
}

export default Register;