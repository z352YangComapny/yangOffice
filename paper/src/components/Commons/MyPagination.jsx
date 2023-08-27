import React from 'react'
import { Pagination, PaginationItem, PaginationLink } from 'reactstrap';

const MyPagination = () => {
    return (
        <>
          <nav aria-label="Page navigation example">
            <Pagination
              className="pagination justify-content-center"
              listclassName="justify-content-center"
            >
              <PaginationItem>
                <PaginationLink
                  href="#pablo"
                  onClick={e => e.preventDefault()}
                  tabindex="-1"
                >
                  Previous
                </PaginationLink>
              </PaginationItem>
              <PaginationItem>
                <PaginationLink href="#pablo" onClick={e => e.preventDefault()}>
                  1
                </PaginationLink>
              </PaginationItem>
              <PaginationItem>
                <PaginationLink href="#pablo" onClick={e => e.preventDefault()}>
                  2
                </PaginationLink>
              </PaginationItem>
              <PaginationItem>
                <PaginationLink href="#pablo" onClick={e => e.preventDefault()}>
                  3
                </PaginationLink>
              </PaginationItem>
              <PaginationItem>
                <PaginationLink href="#pablo" onClick={e => e.preventDefault()}>
                  Next
                </PaginationLink>
              </PaginationItem>
            </Pagination>
          </nav>
        </>
      );
}

export default MyPagination