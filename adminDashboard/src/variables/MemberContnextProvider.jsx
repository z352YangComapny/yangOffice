import React, { createContext, useState } from 'react';
import axios from 'axios';

export const MemberContext = createContext()

const MemberContextProvider = (props) => {
    const [memberList , setMemberList ] = useState([]);
    const [memberTotalCount , setMemberTotalCount] = useState(0);
    const [memberPage , setMemberPage] = useState(0);
    const [memberTotalPages, setMemberTotalPages] = useState(0);

    const getMember = (id) => {}
    const getMemberList = () => {}
    const createMember = (member) => {}
    const updateMember = (member) => {}
    const deletedMember = (id) => {}
    const getMemberTotlaCount = async () => {
      return await axios.get(`/api/v1/memberCount`)
    }

    
    const value = {
      states : {
        memberList, 
        memberPage,
        memberTotalPages,
        memberTotalCount
      },
      actions: {
        getMember,
        getMemberList,
        createMember,
        updateMember,
        deletedMember,
        getMemberTotlaCount,
        setMemberTotalCount
      }
    };

  return (
    <MemberContext.Provider value={value}>
        {props.children}
    </MemberContext.Provider>
  )
}

export default MemberContextProvider