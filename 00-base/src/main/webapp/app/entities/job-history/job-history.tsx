import React from 'react';
import InfiniteScroll from 'react-infinite-scroller';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities, reset } from './job-history.reducer';
import { IJobHistory } from 'app/shared/model/job-history.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IJobHistoryProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IJobHistoryState = IPaginationBaseState;

export class JobHistory extends React.Component<IJobHistoryProps, IJobHistoryState> {
  state: IJobHistoryState = {
    ...getSortState(this.props.location, ITEMS_PER_PAGE)
  };

  componentDidMount() {
    this.reset();
  }

  componentDidUpdate() {
    if (this.props.updateSuccess) {
      this.reset();
    }
  }

  reset = () => {
    this.props.reset();
    this.setState({ activePage: 1 }, () => {
      this.getEntities();
    });
  };

  handleLoadMore = () => {
    if (window.pageYOffset > 0) {
      this.setState({ activePage: this.state.activePage + 1 }, () => this.getEntities());
    }
  };

  sort = prop => () => {
    this.setState(
      {
        order: this.state.order === 'asc' ? 'desc' : 'asc',
        sort: prop
      },
      () => {
        this.reset();
      }
    );
  };

  getEntities = () => {
    const { activePage, itemsPerPage, sort, order } = this.state;
    this.props.getEntities(activePage - 1, itemsPerPage, `${sort},${order}`);
  };

  render() {
    const { jobHistoryList, match } = this.props;
    return (
      <div>
        <h2 id="job-history-heading">
          <Translate contentKey="benchmarkApp.jobHistory.home.title">Job Histories</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="benchmarkApp.jobHistory.home.createLabel">Create a new Job History</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <InfiniteScroll
            pageStart={this.state.activePage}
            loadMore={this.handleLoadMore}
            hasMore={this.state.activePage - 1 < this.props.links.next}
            loader={<div className="loader">Loading ...</div>}
            threshold={0}
            initialLoad={false}
          >
            {jobHistoryList && jobHistoryList.length > 0 ? (
              <Table responsive aria-describedby="job-history-heading">
                <thead>
                  <tr>
                    <th className="hand" onClick={this.sort('id')}>
                      <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                    </th>
                    <th className="hand" onClick={this.sort('startDate')}>
                      <Translate contentKey="benchmarkApp.jobHistory.startDate">Start Date</Translate> <FontAwesomeIcon icon="sort" />
                    </th>
                    <th className="hand" onClick={this.sort('endDate')}>
                      <Translate contentKey="benchmarkApp.jobHistory.endDate">End Date</Translate> <FontAwesomeIcon icon="sort" />
                    </th>
                    <th className="hand" onClick={this.sort('language')}>
                      <Translate contentKey="benchmarkApp.jobHistory.language">Language</Translate> <FontAwesomeIcon icon="sort" />
                    </th>
                    <th>
                      <Translate contentKey="benchmarkApp.jobHistory.job">Job</Translate> <FontAwesomeIcon icon="sort" />
                    </th>
                    <th>
                      <Translate contentKey="benchmarkApp.jobHistory.department">Department</Translate> <FontAwesomeIcon icon="sort" />
                    </th>
                    <th>
                      <Translate contentKey="benchmarkApp.jobHistory.employee">Employee</Translate> <FontAwesomeIcon icon="sort" />
                    </th>
                    <th />
                  </tr>
                </thead>
                <tbody>
                  {jobHistoryList.map((jobHistory, i) => (
                    <tr key={`entity-${i}`}>
                      <td>
                        <Button tag={Link} to={`${match.url}/${jobHistory.id}`} color="link" size="sm">
                          {jobHistory.id}
                        </Button>
                      </td>
                      <td>
                        <TextFormat type="date" value={jobHistory.startDate} format={APP_DATE_FORMAT} />
                      </td>
                      <td>
                        <TextFormat type="date" value={jobHistory.endDate} format={APP_DATE_FORMAT} />
                      </td>
                      <td>
                        <Translate contentKey={`benchmarkApp.Language.${jobHistory.language}`} />
                      </td>
                      <td>{jobHistory.job ? <Link to={`job/${jobHistory.job.id}`}>{jobHistory.job.id}</Link> : ''}</td>
                      <td>
                        {jobHistory.department ? <Link to={`department/${jobHistory.department.id}`}>{jobHistory.department.id}</Link> : ''}
                      </td>
                      <td>{jobHistory.employee ? <Link to={`employee/${jobHistory.employee.id}`}>{jobHistory.employee.id}</Link> : ''}</td>
                      <td className="text-right">
                        <div className="btn-group flex-btn-group-container">
                          <Button tag={Link} to={`${match.url}/${jobHistory.id}`} color="info" size="sm">
                            <FontAwesomeIcon icon="eye" />{' '}
                            <span className="d-none d-md-inline">
                              <Translate contentKey="entity.action.view">View</Translate>
                            </span>
                          </Button>
                          <Button tag={Link} to={`${match.url}/${jobHistory.id}/edit`} color="primary" size="sm">
                            <FontAwesomeIcon icon="pencil-alt" />{' '}
                            <span className="d-none d-md-inline">
                              <Translate contentKey="entity.action.edit">Edit</Translate>
                            </span>
                          </Button>
                          <Button tag={Link} to={`${match.url}/${jobHistory.id}/delete`} color="danger" size="sm">
                            <FontAwesomeIcon icon="trash" />{' '}
                            <span className="d-none d-md-inline">
                              <Translate contentKey="entity.action.delete">Delete</Translate>
                            </span>
                          </Button>
                        </div>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </Table>
            ) : (
              <div className="alert alert-warning">
                <Translate contentKey="benchmarkApp.jobHistory.home.notFound">No Job Histories found</Translate>
              </div>
            )}
          </InfiniteScroll>
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ jobHistory }: IRootState) => ({
  jobHistoryList: jobHistory.entities,
  totalItems: jobHistory.totalItems,
  links: jobHistory.links,
  entity: jobHistory.entity,
  updateSuccess: jobHistory.updateSuccess
});

const mapDispatchToProps = {
  getEntities,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(JobHistory);
