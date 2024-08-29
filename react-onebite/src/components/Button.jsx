const Button = ({text, color, children}) => {
    console.log(children)
    return (<button style={{color: color}}>
        {text}
        {children}
    </button>)
}

Button.defaultProps = {
    color : "black"
}

export default Button;