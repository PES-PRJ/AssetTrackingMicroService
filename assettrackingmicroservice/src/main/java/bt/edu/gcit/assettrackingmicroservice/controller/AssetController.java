package bt.edu.gcit.assettrackingmicroservice.controller;

import bt.edu.gcit.assettrackingmicroservice.entity.Asset;
import bt.edu.gcit.assettrackingmicroservice.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity; // MISSING IMPORT

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    @Autowired
    private AssetRepository assetRepository;

    // 1. CREATE (You already have this)
    @PostMapping
    public Asset createAsset(@RequestBody Asset asset) {
        return assetRepository.save(asset);
    }

    // 2. READ ALL (You already have this)
    @GetMapping
    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    // 3. READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<Asset> getAssetById(@PathVariable Long id) {
        return assetRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 4. UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Asset> updateAsset(@PathVariable Long id, @RequestBody Asset assetDetails) {
        return assetRepository.findById(id).map(asset -> {
            asset.setName(assetDetails.getName());
            asset.setAssetTag(assetDetails.getAssetTag());
            asset.setStatus(assetDetails.getStatus());
            return ResponseEntity.ok(assetRepository.save(asset));
        }).orElse(ResponseEntity.notFound().build());
    }

    // 5. DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsset(@PathVariable Long id) {
        return assetRepository.findById(id).map(asset -> {
            assetRepository.delete(asset);
            return ResponseEntity.ok().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}