import React, { createContext, useState } from 'react';
import axios from 'axios';

export const MemberContext = createContext()

const MemberContextProvider = (props) => {
  const [memberTotalCount, setMemberTotalCount] = useState(0);
  const [memberPage, setMemberPage] = useState(0);
  const [memberTotalPages, setMemberTotalPages] = useState(0);

  const getMember = (id) => { }
  const getMemberPage = async (pageNo) => {
    return await axios.get(`/api/v1/memberPage/${pageNo}`)
  }
  const createMember = (member) => { }
  const updateMember = async (member) => {
    return await axios.post(`/api/v1/memberUpdate`, member)
  }
  const deletedMember = (id) => { }
  
  const getMemberTotlaCount = async () => {
    return await axios.get(`/api/v1/memberCount`)
  }


  const value = {
    states: {
      memberPage,
      memberTotalPages,
      memberTotalCount
    },
    actions: {
      getMember,
      createMember,
      updateMember,
      deletedMember,
      getMemberPage,
      setMemberPage,
      getMemberTotlaCount,
      setMemberTotalCount,
      setMemberTotalPages
    }
  };

  return (
    <MemberContext.Provider value={value}>
      {props.children}
    </MemberContext.Provider>
  )
}

export default MemberContextProvider