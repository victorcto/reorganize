import axios from 'axios';

const axiosReorganize = axios.create({
  baseURL: 'http://172.17.0.1:8082/'
});

export default axiosReorganize;