package bt.edu.gcit.assettracking.dao;

import bt.edu.gcit.assettracking.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {
    Optional<Asset> findBySerialNo(String serialNo);
    List<bt.edu.gcit.assettracking.entity.Asset> findByAssignedTo(String email);
}