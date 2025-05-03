// src/services/paymentService.js
import axios from '../utils/axiosCustomize';

export const createPaymentUrl = async (orderId) => {
    return axios.post('/v1/api/payment/create-payment', { orderId });
};
