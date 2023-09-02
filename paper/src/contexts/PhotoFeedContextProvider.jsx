import axios from 'axios';
import React, { createContext, useState } from 'react'

export const PhotoFeedContext = createContext();
const SpringBaseURL = "http://localhost:8080"


const PhotoFeedContextProvider = (props) => {
    const [maxPage, setMaxPage] = useState(1);
    const [data, setData] = useState([]);
    const [selectedFeed, setSelectedFeed] = useState({});
    const [isDetail, setIsDetail] = useState(false);
    

    const Authorization = {
        headers: {
            "Authorization": sessionStorage.getItem('token'),
        }
    };

    const getTotalPage = async () => {
        return await axios.get(SpringBaseURL + '/api/v1/photoFeedCount')
    }

    const getFeedPage = async (page, hostname) => {
        return await axios.get(SpringBaseURL + `/feed/feed?username=${hostname}&page=${page}`)
    }
    const insertFeed = async (formData) => {
        return axios.post(SpringBaseURL + `/feed/feedCreate`, formData, Authorization)
    }

    const value = {
        states: {
            selectedFeed,
            data,
            maxPage,
            isDetail
        },
        actions: {
            setSelectedFeed,
            setMaxPage,
            setData,
            getTotalPage,
            getFeedPage,
            setIsDetail,
            insertFeed
        },
    }


    return (
        <PhotoFeedContext.Provider value={value}>
            {props.children}
        </PhotoFeedContext.Provider>

    )
}

export default PhotoFeedContextProvider