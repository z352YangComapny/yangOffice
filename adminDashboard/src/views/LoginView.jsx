import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Container from 'react-bootstrap/Container';
import Navbar from 'react-bootstrap/Navbar';
import { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const LoginView = () => {
    const [isLogin, setIsLogin] = useState(false);
    const [count , setCount ] = useState(0);
    const navigate = useNavigate();
    
    const getAuth = async () => {
        const token = localStorage.getItem("Authorization");
        await axios.get(`/admin`,{
            headers:{
                Authorization: token
            }
        })
        .then((resp)=>{
            navigate('/admin/dashboard')
        })
        .catch((err)=>{
            console.log(err)
            if(count>0)
            alert('권한이 없습니다.')
            setCount(count+1)
        })
    }

    useEffect(() => {
        getAuth()
    }, [])

    const handleOnSubmit =async (e) => {
        e.preventDefault();
        const username = e.target[0].value;
        const password = e.target[1].value;
        
        await axios.post(`/login`,{
            username:username,
            password:password
        })
        .then((resp)=> {
            console.log(resp)
            const token = resp.headers.authorization
            localStorage.setItem('Authorization', token)
            getAuth();
        })
        .catch((err)=>{
            console.log(err)
            alert('로그인 실패')
        })
    }

    return (
        <div>
            <Container className="panel">
                <Navbar className="bg-body-tertiary">
                    <Container>
                        <Navbar.Brand> Yang World Admin Page</Navbar.Brand>
                    </Container>
                </Navbar>
                <div style={{ height: "80px" }}></div>
                <Form onSubmit={handleOnSubmit}>
                    <Form.Group as={Row} className="mb-3" controlId="formPlaintextPassword">
                        <Col />
                        <Col sm>
                            <Form.Control type="text" placeholder="UserID" />
                        </Col>
                        <Col />
                    </Form.Group>

                    <Form.Group as={Row} className="mb-3" controlId="formPlaintextPassword">
                        <Col />
                        <Col sm>
                            <Form.Control type="password" placeholder="Password" />
                        </Col>
                        <Col />
                    </Form.Group>
                    <br />

                    <div className="d-grid gap-1" style={{ display: "flex", justifyContent: "center" }}>
                        <Button variant="secondary" type="submit" >
                            Sign In
                        </Button>
                    </div>
                </Form>
            </Container>
        </div>
    );

}

export default LoginView