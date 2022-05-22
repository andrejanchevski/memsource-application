import axios from "axios";

export const axiosClient = axios.create({
    baseURL: `http://localhost:8080/api`,
    headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        "Access-Control-Allow-Origin": "*"
    }
});

axiosClient.interceptors.request.use(
    config => {
        console.log(config)
    },
    error =>{
        return Promise.reject(error)
    }
)

axiosClient.interceptors.response.use(
    function (response) {
        return response;
    },
    function (error) {
        return Promise.reject(error);
    }
);