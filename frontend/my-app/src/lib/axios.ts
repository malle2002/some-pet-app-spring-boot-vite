import Axios from "axios";

const axios = Axios.create({
    baseURL: import.meta.env.VITE_API_URL || "http://localhost:8081",
    timeout: 10000,
    headers: {
        "Content-Type": "application/json",
    },
});

// Example of a request interceptor
axios.interceptors.request.use(
    (config) => {
        // Example: Attach token if needed
        const token = localStorage.getItem("token");
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    (error) => Promise.reject(error)
);

axios.interceptors.response.use(
    (response) => response,
    (error) => {
        console.error("API Error:", error);
        return Promise.reject(error);
    }
);

export default axios;
