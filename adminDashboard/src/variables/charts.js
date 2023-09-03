/*!

=========================================================
* Paper Dashboard React - v1.3.2
=========================================================

* Product Page: https://www.creative-tim.com/product/paper-dashboard-react
* Copyright 2023 Creative Tim (https://www.creative-tim.com)

* Licensed under MIT (https://github.com/creativetimofficial/paper-dashboard-react/blob/main/LICENSE.md)

* Coded by Creative Tim

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

*/
const labels = [];
const currentDate = new Date();
// 현재일자부터 10일 전까지의 레이블 생성
for (let i = 9; i >= 0; i--) {
  const date = new Date(currentDate);
  date.setDate(currentDate.getDate() - i); // 현재일자에서 i일 전의 날짜 계산
  const month = date.getMonth() + 1; // 월은 0부터 시작하므로 +1
  const day = date.getDate();
  const formattedDate = `${month}/${day}`; // 월과 일을 문자열로 합침
  labels.push(formattedDate);
}
let photofeedValues = [];
let storyValues = [];
let guestbookValues = [];

let memberCountValues = [];
let deletedMemberCountValues = [];

let memberCountByProvider = [];

const dashboard24HoursPerformanceChart = {


  data: (canvas) => {
    fetch(`http://localhost:8080/api/v1/photoFeed/dailyPhotoFeed`)
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        // ReadableStream을 텍스트로 읽기
        return response.json();
      })
      .then((data) => {
        photofeedValues = [];
        data.map((value, index)=>{
          photofeedValues.push(value.photofeedCount)
        })
        console.log(photofeedValues)
      })
      .catch((error) => {
        console.error(error);
      });


      fetch(`http://localhost:8080/api/v1/story/dailyStory`)
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        // ReadableStream을 텍스트로 읽기
        return response.json();
      })
      .then((data) => {
        storyValues = [];
        data.map((value, index)=>{
          storyValues.push(value.storyCount)
        })
        console.log(storyValues)
      })
      .catch((error) => {
        console.error(error);
      });

      fetch(`http://localhost:8080/api/v1/guestbook/dailyGuestbook`)
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        // ReadableStream을 텍스트로 읽기
        return response.json();
      })
      .then((data) => {
        guestbookValues = [];
        data.map((value, index)=>{
          guestbookValues.push(value.guestbookCount)
        })
        console.log(guestbookValues)
      })
      .catch((error) => {
        console.error(error);
      });

    return {
      labels: labels,
      datasets: [
        {
          borderColor: "#6bd098",
          backgroundColor: "#6bd098",
          pointRadius: 0,
          pointHoverRadius: 0,
          borderWidth: 3,
          tension: 0.4,
          fill: true,
          data: photofeedValues,
        },
        {
          borderColor: "#f17e5d",
          backgroundColor: "#f17e5d",
          pointRadius: 0,
          pointHoverRadius: 0,
          borderWidth: 3,
          tension: 0.4,
          fill: true,
          data: storyValues,
        },
        {
          borderColor: "#fcc468",
          backgroundColor: "#fcc468",
          pointRadius: 0,
          pointHoverRadius: 0,
          borderWidth: 3,
          tension: 0.4,
          fill: true,
          data: guestbookValues,
        },
      ],
    };
  },
  options: {
    plugins: {
      legend: { display: false },
      tooltip: { enabled: false },
    },
    scales: {
      y: {
        ticks: {
          color: "#9f9f9f",
          beginAtZero: false,
          maxTicksLimit: 5,
        },
        grid: {
          drawBorder: false,
          display: false,
        },
      },
      x: {
        barPercentage: 1.6,
        grid: {
          drawBorder: false,
          display: false,
        },
        ticks: {
          padding: 20,
          color: "#9f9f9f",
        },
      },
    },
  },
};

const dashboardEmailStatisticsChart = {
  data: (canvas) => {

    fetch(`http://localhost:8080/member/OAuthMemberCount`)
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        // ReadableStream을 텍스트로 읽기
        return response.json();
      })
      .then((data) => {
        memberCountByProvider = [];
        data.map((value, index)=>{
          memberCountByProvider.push(value.memberCount)
        })
        console.log(memberCountByProvider);
    
      })
      .catch((error) => {
        console.error(error);
      });

    return {
      
      labels: [1, 2, 3, 4, 5],
      datasets: [
        {
          label: "Emails",
          pointRadius: 0,
          pointHoverRadius: 0,
          backgroundColor: ["#60b19e", "#ef8157", "#fcc468", "#4acccd", "#c98bc9"],
          borderWidth: 0,
          data: memberCountByProvider,
        },
      ],
    };
  },
  options: {
    plugins: {
      legend: { display: false },
      tooltip: { enabled: false },
    },
    maintainAspectRatio: false,
    pieceLabel: {
      render: "percentage",
      fontColor: ["white"],
      precision: 2,
    },
    scales: {
      y: {
        ticks: {
          display: false,
        },
        grid: {
          drawBorder: false,
          display: false,
        },
      },
      x: {
        barPercentage: 1.6,
        grid: {
          drawBorder: false,
          display: false,
        },
        ticks: {
          display: false,
        },
      },
    },
  },
};

const dashboardNASDAQChart = {
  data: (canvas) => {


    fetch(`http://localhost:8080/member/memberCount`)
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        // ReadableStream을 텍스트로 읽기
        return response.json();
      })
      .then((data) => {
        memberCountValues = [];
        data.map((value, index)=>{
          memberCountValues.push(value.memberCount)
        })
        console.log(memberCountValues)
      })
      .catch((error) => {
        console.error(error);
      });

      fetch(`http://localhost:8080/member/deletedMemberCount`)
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        // ReadableStream을 텍스트로 읽기
        return response.json();
      })
      .then((data) => {
        deletedMemberCountValues = [];
        data.map((value, index)=>{
          deletedMemberCountValues.push(value.memberCount)
        })
        console.log(deletedMemberCountValues)
      })
      .catch((error) => {
        console.error(error);
      });

    return {
      labels: [
        "Jan",
        "Feb",
        "Mar",
        "Apr",
        "May",
        "Jun",
        "Jul",
        "Aug",
        "Sep",
        "Oct",
        "Nov",
        "Dec",
      ],
      datasets: [
        {
          data: memberCountValues,
          fill: false,
          borderColor: "#fbc658",
          backgroundColor: "transparent",
          pointBorderColor: "#fbc658",
          pointRadius: 4,
          pointHoverRadius: 4,
          pointBorderWidth: 8,
          tension: 0.4,
        },
        {
          data: deletedMemberCountValues,
          fill: false,
          borderColor: "#51CACF",
          backgroundColor: "transparent",
          pointBorderColor: "#51CACF",
          pointRadius: 4,
          pointHoverRadius: 4,
          pointBorderWidth: 8,
          tension: 0.4,
        },
      ],
    };
  },
  options: {
    plugins: {
      legend: { display: false },
    },
  },
};

module.exports = {
  dashboard24HoursPerformanceChart,
  dashboardEmailStatisticsChart,
  dashboardNASDAQChart,
  photofeedValues,
  storyValues,
  guestbookValues

};
