const express = require('express');
const router = express.Router();

const resApiController = require('../../api/ResidentAPI');

router.get('/', resApiController.getResidents);
router.get('/:resId', resApiController.getResidentById);
router.post('/', resApiController.createResident);
router.put('/:resId', resApiController.updateResident);
router.delete('/:resId', resApiController.deleteResident);

module.exports = router;