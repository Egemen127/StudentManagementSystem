import axios from "axios"

const API_URL = "http://localhost:8080"

export function authenticate(loginData) {
    
    return axios(API_URL+"/authenticate",{method:"post",data: loginData})
    
}

export function studentDetails() {
    axios.defaults.headers.common['Authorization'] = "Bearer " + localStorage.getItem("token");
    return axios.get(API_URL+"/student/")
}