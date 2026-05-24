package bt.edu.gcit.assettracking.service;

import bt.edu.gcit.assettracking.entity.Asset;
import java.util.List;

public interface AssetService {
    Asset saveAsset(Asset asset);
    List<Asset> getAllAssets();
    Asset getAssetById(Long id);
    Asset updateAsset(Long id, Asset assetDetails);
    void deleteAsset(Long id);
    
    // Add this line:
    List<Asset> getAssetsByEmployee(String email); 
}