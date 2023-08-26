import React from 'react'
import { Card, Col, Row } from 'reactstrap'
import '../../assets/css/photofeed.css'

const PhotoFeedComponent = () => {
    return (
        <Card style={{
            border: 'solid 3px rgba(81, 203, 206, 1)',
            transform: 'none',
            transition: 'none',
            marginTop: "20px",
            boxShadow: '4px 4px 8px rgba(81, 203, 206, 0.8)',
            height: "65%"
        }}>
            <Row className='feed'>
                <Col md={7} className='feed-detail'>1234124</Col>
                <Col md={5} className='feed-total'>132456123</Col>
            </Row>
        </Card>
    )
}

export default PhotoFeedComponent
