import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './department.reducer';
import { IDepartment } from 'app/shared/model/department.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDepartmentDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class DepartmentDetail extends React.Component<IDepartmentDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { departmentEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="benchmarkApp.department.detail.title">Department</Translate> [<b>{departmentEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="departmentName">
                <Translate contentKey="benchmarkApp.department.departmentName">Department Name</Translate>
              </span>
            </dt>
            <dd>{departmentEntity.departmentName}</dd>
            <dt>
              <Translate contentKey="benchmarkApp.department.location">Location</Translate>
            </dt>
            <dd>{departmentEntity.location ? departmentEntity.location.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/department" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/department/${departmentEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ department }: IRootState) => ({
  departmentEntity: department.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(DepartmentDetail);
