package bt.edu.gcit.assettracking.rest;

import bt.edu.gcit.assettracking.entity.Asset;
import bt.edu.gcit.assettracking.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.security.Principal;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @PostMapping
    public ResponseEntity<Asset> createAsset(@RequestBody Asset asset) {
        return ResponseEntity.ok(assetService.saveAsset(asset));
    }

    @GetMapping
    public ResponseEntity<List<Asset>> getAllAssets() {
        return ResponseEntity.ok(assetService.getAllAssets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asset> getAssetById(@PathVariable Long id) {
        return ResponseEntity.ok(assetService.getAssetById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asset> updateAsset(@PathVariable Long id, @RequestBody Asset assetDetails) {
        return ResponseEntity.ok(assetService.updateAsset(id, assetDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAsset(@PathVariable Long id) {
        assetService.deleteAsset(id);
        return ResponseEntity.ok("Asset with ID " + id + " deleted successfully.");
    }

    @GetMapping("/my-assets")
    public ResponseEntity<List<Asset>> getMyAssets(Principal principal) {
        // principal.getName() extracts the subject (email) embedded inside the JWT
        String email = principal.getName();
        return ResponseEntity.ok(assetService.getAssetsByEmployee(email));
    }
}