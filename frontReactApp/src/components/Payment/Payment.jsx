import MyPagination from "components/Commons/MyPagination";
import React from "react";
import {
  Button,
  Card,
  CardBody,
  CardHeader,
  CardTitle,
  Table,
} from "reactstrap";

const Payment = () => {
  return (
    <Card
      style={{
        border: "solid 3px rgba(81, 203, 206, 1)",
        transform: "none",
        transition: "none",
        marginTop: "20px",
        width: "100%",
        height: "40.9%",
        boxShadow: "4px 4px 8px rgba(81, 203, 206, 0.8)",
      }}
    >
      <CardHeader
        style={{
          display: "flex",
          justifyContent: "space-between",
          backgroundColor: "transparent",
          paddingTop: "15px",
        }}
      >
        <CardTitle
          tag="h5"
          style={{ fontWeight: "600", color: "rgba(81, 203, 206, 0.8)" }}
        >
          #Point Shop
        </CardTitle>
        <div style={{ width: "67%" }}>
          <MyPagination></MyPagination>
        </div>
      </CardHeader>
      <CardBody style={{ display: "flex", padding: "0px", height: "100%" }}>
        <div className="pointshop-body pointshop-body-left">
          <div className="current-point">
            <div className="grade">🎉Vip🎉 grade</div>
            <div className="totla-cash">
              누적 결제 금액 <br /> 400,000,000₩{" "}
            </div>
            <div className="rest-month-cash">
              이번달 잔여 한도 <br /> 1,500,000₩{" "}
            </div>
            <div className="rest-cash">
              남은 잔액 <br />
              12,000,000₩{" "}
            </div>
            <Button color="primary" style={{ justifySelf: "end" }}>
              충전하기
            </Button>
          </div>
        </div>
        <Table className="purchase-list" hover>
          <thead>
            <tr>
              <th>#</th>
              <th>구매 상품</th>
              <th>금액</th>
              <th>구매 시각</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <th scope="row">1</th>
              <td>Mark</td>
              <td>Otto</td>
              <td>@mdo</td>
            </tr>
            <tr>
              <th scope="row">1</th>
              <td>Mark</td>
              <td>Otto</td>
              <td>@mdo</td>
            </tr>
            <tr>
              <th scope="row">1</th>
              <td>Mark</td>
              <td>Otto</td>
              <td>@mdo</td>
            </tr>
            <tr>
              <th scope="row">1</th>
              <td>Mark</td>
              <td>Otto</td>
              <td>@mdo</td>
            </tr>
            <tr>
              <th scope="row">1</th>
              <td>Mark</td>
              <td>Otto</td>
              <td>@mdo</td>
            </tr>
          </tbody>
        </Table>
      </CardBody>
    </Card>
  );
};

export default Payment;
