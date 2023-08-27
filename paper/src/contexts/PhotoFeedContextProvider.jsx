import axios from 'axios';
import React, { createContext, useState } from 'react'

export const PhotoFeedContext = createContext();
const SpringBaseURL = "http://localhost:8080"

const axiosConfig = {
    headers: {
        "Content-Type": "application/json",
        "Authorization": sessionStorage.getItem('token')
    }
}

const PhotoFeedContextProvider = (props) => {
    const [maxPage, setMaxPage] = useState(1);
    const [data, setData] = useState([]);
    const [selectedFeed, setSelectedFeed] = useState({});


    const getTotalPage = async () => {
        return await axios.get(SpringBaseURL + '/api/v1/photoFeedCount')
    }

    const getFeedPage = async (pageNo) => {
        console.log(pageNo)
        return await axios.get(SpringBaseURL + `/api/v1/feed/cli/${pageNo}`)
    }

    const value = {
        states: {
            selectedFeed,
            data,
            maxPage
        },
        actions: {
            setSelectedFeed,
            setMaxPage,
            setData,
            getTotalPage,
            getFeedPage
        },
    }


    return (
        <PhotoFeedContext.Provider value={value}>
            {props.children}
        </PhotoFeedContext.Provider>

    )
}

export default PhotoFeedContextProvider