package bt.edu.gcit.assettrackingmicroservice.repository;

import bt.edu.gcit.assettrackingmicroservice.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset, Long> {
}