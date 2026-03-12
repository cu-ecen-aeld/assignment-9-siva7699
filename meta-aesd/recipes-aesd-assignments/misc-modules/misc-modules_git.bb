# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f098732a73b5f6f3430472f5b094ffdb"

SRC_URI = "git://github.com/cu-ecen-aeld/assignment-7-siva7699.git;protocol=https;branch=main"
SRC_URI += " file://misc-init"
# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "732eb91bf9b765d6d7267b568049e82f00a4460b"

S = "${WORKDIR}/git"

inherit module update-rc.d

INITSCRIPT_NAME = "misc-init"
INITSCRIPT_PARAMS = "defaults 98"

EXTRA_OEMAKE += " -C ${STAGING_KERNEL_DIR} M=${S}/misc-modules EXTRA_CFLAGS=-I${S}/include"

do_install() {
    module_do_install
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/misc-init ${D}${sysconfdir}/init.d/misc-init
}

FILES:${PN} += "${sysconfdir}/init.d/misc-init"