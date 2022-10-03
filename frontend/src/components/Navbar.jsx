import React from "react";
import Button from "@mui/material/Button";
import { useNavigate } from "react-router-dom";


export default function Navbar(props) {
    const nav = useNavigate()
    return  <div variant="text" style={{"position":"fixed","left":0,"top":5,
    "width":"100%","backgroundColor":"#f1f1f1","display":"flex","padding":"10px 70px","justifyContent":"space-evenly"}}aria-label="outlined primary button group">
                <Button>Home</Button>
                <Button>Login</Button>
                <Button onClick={()=>{localStorage.clear()
                    nav("/")}}>Logout</Button>
                <Button>About Us</Button>
                <Button>Dashboard</Button>
            </div>
    
}