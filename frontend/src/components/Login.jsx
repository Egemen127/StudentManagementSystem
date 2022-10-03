import React,{useState,useEffect} from "react";
import Label from "@mui/material/InputLabel"
import Input from "@mui/material/Input";
import Typography  from "@mui/material/Typography";
import Paper from "@mui/material/Paper"
import Button from "@mui/material/Button"
import { createClient } from 'pexels';
import { authenticate } from "../ApiUtil/ApiUtilFunctions";
import { useNavigate } from "react-router-dom";



export default function Login(props) {
    //gets the photo on the page
    const client = createClient('563492ad6f91700001000001ec66ef791bdb4d5aa9da3d9bc1e2013f');
    const [photo,setPhoto] = useState("");
    const query = "school"
    useEffect(()=>{client.photos.search({query}).then(data => {
        setPhoto(data.photos[Math.floor(Math.random()*data.photos.length)].src.medium)
        }).catch(console.log("error"))},[])
    

    //handles form data
    const nav = useNavigate()
    const [formData,setFormData] = useState({username:"",password:""})
    
    const handleChange = (e) => {
        setFormData((prev)=> ({...prev, [e.target.name]: e.target.value}))
        console.log(formData)
    }
    //stores the jwt in the localstorage and navigates to dashboard page
    const handleSubmit = (e) =>{
    e.preventDefault() 
    authenticate(formData).then(res => {
    localStorage.setItem("token", res.data.token)
    localStorage.setItem("current_user",res.data.currentUser)
    localStorage.setItem("authorities",res.data.authorities)
    nav("/dashboard")})
}

    
    return <Paper elevation={20} style={{ "display":"flex","flexDirection": "column","alignItems":"center","padding":20, "backgroundColor":"white"}}>
            <Typography variant="h1">Login</Typography>
            <img src={photo}/>
            <Label>Username</Label>
            <Input 
                onChange={handleChange}
                placeholder="Username" 
                name="username"
                value={formData.username} 
                type="text">
            </Input>
            <Label>Password</Label>
            <Input 
                onChange={handleChange} 
                placeholder="Password"
                name="password"
                value={formData.password} 
                type="password">
            </Input>
            <br/>
            <Button variant="contained"onClick={handleSubmit}>Submit</Button>
            </Paper>


}