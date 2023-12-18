import { residentList, residentDetailsList } from './residentApiMockData'

export function getResidentsApiCall() {
    return residentList;
}

export function getResidentByIdApiCall(resId) {
    const res = residentDetailsList.find(res => res._id === resId)
    return res;
}