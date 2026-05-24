package bt.edu.gcit.assettracking.service;

import bt.edu.gcit.assettracking.dao.AssetRepository;
import bt.edu.gcit.assettracking.entity.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetRepository assetRepository;

    @Override
    public Asset saveAsset(Asset asset) {
        return assetRepository.save(asset);
    }

    @Override
    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    @Override
    public Asset getAssetById(Long id) {
        return assetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asset not found with id: " + id));
    }

    @Override
    public Asset updateAsset(Long id, Asset assetDetails) {
        Asset asset = getAssetById(id);
        
        asset.setSerialNo(assetDetails.getSerialNo());
        asset.setName(assetDetails.getName());
        asset.setLocation(assetDetails.getLocation());
        asset.setCategory(assetDetails.getCategory());
        asset.setAssignedTo(assetDetails.getAssignedTo());
        
        return assetRepository.save(asset);
    }

    @Override
    public void deleteAsset(Long id) {
        Asset asset = getAssetById(id);
        assetRepository.delete(asset);
    }

    // Keep your existing methods and append this one at the bottom:

    @Override
    public List<Asset> getAssetsByEmployee(String email) {
        return assetRepository.findByAssignedTo(email);
    }
}