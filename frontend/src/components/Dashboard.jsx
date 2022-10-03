import { Typography } from "@mui/material";
import React, { useEffect,useState } from "react"
import { useNavigate } from "react-router-dom"
import { studentDetails } from "../ApiUtil/ApiUtilFunctions";



export default function Dashboard(props) {
   const nav = useNavigate();
   const [details,setDetails] = useState({})
  useEffect(
    ()=>{
  if(!localStorage.getItem("token")) {
    nav("/")} else {
     studentDetails().then(res => setDetails(res.data.body))
    }
  },[])

    
    return <>
    <Typography variant="h4">hello, {localStorage.getItem("current_user")}</Typography>
    <p>Your role is, {localStorage.getItem("authorities")}</p>
    <p>{JSON.stringify(details,null,2)}</p>
    </>
}