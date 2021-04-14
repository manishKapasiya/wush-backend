package com.wush.demand.db.repository;

import com.wush.demand.db.model.WashDemand;
import org.springframework.data.repository.CrudRepository;

public interface DemandRepository extends CrudRepository<WashDemand,Long> {
}
