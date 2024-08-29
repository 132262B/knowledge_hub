const Button = ({text, color, children}) => {
    const onClick = (e) => {
        console.log(e)
        console.log(text)
    }

    console.log(children)
    return (
        <button style={{color: color}} onClick={onClick}>
        {text}
        {children}
        </button>
    )
}

Button.defaultProps = {
    color : "black"
}

export default Button;