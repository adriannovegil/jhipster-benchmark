import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './location.reducer';
import { ILocation } from 'app/shared/model/location.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ILocationDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class LocationDetail extends React.Component<ILocationDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { locationEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="benchmarkApp.location.detail.title">Location</Translate> [<b>{locationEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="streetAddress">
                <Translate contentKey="benchmarkApp.location.streetAddress">Street Address</Translate>
              </span>
            </dt>
            <dd>{locationEntity.streetAddress}</dd>
            <dt>
              <span id="postalCode">
                <Translate contentKey="benchmarkApp.location.postalCode">Postal Code</Translate>
              </span>
            </dt>
            <dd>{locationEntity.postalCode}</dd>
            <dt>
              <span id="city">
                <Translate contentKey="benchmarkApp.location.city">City</Translate>
              </span>
            </dt>
            <dd>{locationEntity.city}</dd>
            <dt>
              <span id="stateProvince">
                <Translate contentKey="benchmarkApp.location.stateProvince">State Province</Translate>
              </span>
            </dt>
            <dd>{locationEntity.stateProvince}</dd>
            <dt>
              <Translate contentKey="benchmarkApp.location.country">Country</Translate>
            </dt>
            <dd>{locationEntity.country ? locationEntity.country.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/location" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/location/${locationEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ location }: IRootState) => ({
  locationEntity: location.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(LocationDetail);
